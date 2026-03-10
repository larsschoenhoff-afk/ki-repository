import { useEffect, useMemo, useState } from 'react'

const columns = [
  { key: 'name', label: 'Name' },
  { key: 'street', label: 'Str.' },
  { key: 'city', label: 'Ort' },
  { key: 'postalCode', label: 'Postleitzahl' },
  { key: 'phoneNumber', label: 'Telefonnummer' }
]

function App() {
  const [rows, setRows] = useState([])
  const [search, setSearch] = useState('')
  const [pageSize, setPageSize] = useState(10)
  const [currentPage, setCurrentPage] = useState(1)
  const [sortColumn, setSortColumn] = useState(null)
  const [sortDirection, setSortDirection] = useState('asc')
  const [error, setError] = useState('')

  useEffect(() => {
    fetch('http://localhost:8080/api/entries')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Daten konnten nicht geladen werden.')
        }
        return response.json()
      })
      .then((data) => setRows(data))
      .catch((fetchError) => setError(fetchError.message))
  }, [])

  const filteredRows = useMemo(() => {
    const query = search.trim().toLowerCase()
    const filtered = rows.filter((row) =>
      Object.values(row).some((value) => String(value).toLowerCase().includes(query))
    )

    if (!sortColumn) {
      return filtered
    }

    return [...filtered].sort((a, b) => {
      const valueA = String(a[sortColumn]).toLowerCase()
      const valueB = String(b[sortColumn]).toLowerCase()
      if (valueA < valueB) return sortDirection === 'asc' ? -1 : 1
      if (valueA > valueB) return sortDirection === 'asc' ? 1 : -1
      return 0
    })
  }, [rows, search, sortColumn, sortDirection])

  const effectivePageSize = Number.isFinite(pageSize) ? pageSize : Math.max(filteredRows.length, 1)
  const totalPages = Math.max(1, Math.ceil(filteredRows.length / effectivePageSize))
  const safePage = Math.min(currentPage, totalPages)
  const startIndex = (safePage - 1) * effectivePageSize
  const pageRows = filteredRows.slice(startIndex, startIndex + effectivePageSize)

  useEffect(() => {
    setCurrentPage(1)
  }, [search, sortColumn, sortDirection, pageSize])

  function toggleSort(columnKey) {
    if (sortColumn === columnKey) {
      setSortDirection((previousDirection) => (previousDirection === 'asc' ? 'desc' : 'asc'))
      return
    }
    setSortColumn(columnKey)
    setSortDirection('asc')
  }

  return (
    <main className="page">
      <header className="page-header">
        <div className="brand">
          <img className="brand-logo" src="/wpk-logo.svg" alt="WPK Logo" />
          <h1 className="brand-title">Adressübersicht</h1>
        </div>
      </header>

      <div className="table-wrapper">
        <div className="controls">
          <input
            className="search-input"
            type="text"
            placeholder="Suche in Name, Straße, Ort, PLZ oder Telefonnummer ..."
            value={search}
            onChange={(event) => setSearch(event.target.value)}
          />
          <div className="controls-right">
            <label htmlFor="pageSizeSelect" className="page-size-label">Einträge pro Seite</label>
            <select
              id="pageSizeSelect"
              className="page-size-select"
              value={Number.isFinite(pageSize) ? String(pageSize) : 'all'}
              onChange={(event) => setPageSize(event.target.value === 'all' ? Number.POSITIVE_INFINITY : Number(event.target.value))}
            >
              <option value="10">10</option>
              <option value="50">50</option>
              <option value="100">100</option>
              <option value="all">Alle</option>
            </select>
            <span className="hint">Klick auf Spaltenkopf zum Sortieren</span>
          </div>
        </div>

        {error && <p className="error-message">{error}</p>}

        <table>
          <thead>
            <tr>
              {columns.map((column) => (
                <th key={column.key} onClick={() => toggleSort(column.key)}>
                  {column.label}
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            {pageRows.map((row) => (
              <tr key={`${row.name}-${row.street}-${row.phoneNumber}`}>
                <td>{row.name}</td>
                <td>{row.street}</td>
                <td>{row.city}</td>
                <td>{row.postalCode}</td>
                <td>{row.phoneNumber}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <div className="pager">
          <button type="button" onClick={() => setCurrentPage((page) => Math.max(1, page - 1))} disabled={safePage === 1}>Zurück</button>
          <span>Seite {safePage} von {totalPages}</span>
          <button type="button" onClick={() => setCurrentPage((page) => Math.min(totalPages, page + 1))} disabled={safePage === totalPages}>Weiter</button>
        </div>
      </div>
    </main>
  )
}

export default App
