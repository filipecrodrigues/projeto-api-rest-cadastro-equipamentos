import { useState } from 'react';
import { Container } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import PerifericoForm from './components/PerifericoForm';
import PerifericoList from './components/PerifericoList';
import ExcelExportButton from './components/ExcelExportButton';

function App() {
  const [refresh, setRefresh] = useState(false);

  return (
    <Container className="mt-4">
      <h1 className="text-center mb-4">CADASTRO DE EQIPAMENTOS</h1>
      
      <div className="d-flex justify-content-between mb-3">
        <PerifericoForm onAdd={() => setRefresh(!refresh)} />
        <ExcelExportButton />
      </div>

      <PerifericoList refresh={refresh} />
    </Container>
  );
}

export default App;