import { useEffect, useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { getPerifericos, deletePeriferico } from '../services/api';

export default function PerifericoList({ refresh }) {
  const [perifericos, setPerifericos] = useState([]);

  const fetchPerifericos = async () => {
    try {
      const response = await getPerifericos();
      setPerifericos(response.data);
    } catch (error) {
      console.error('Erro ao buscar periféricos:', error);
    }
  };

  useEffect(() => {
    fetchPerifericos();
  }, [refresh]);

  const handleDelete = async (numeroDeSerie) => {
    try {
      await deletePeriferico(numeroDeSerie);
      fetchPerifericos();
    } catch (error) {
      console.error('Erro ao deletar:', error);
    }
  };

  return (
      <Table striped bordered hover>
  <thead>
    <tr>
      <th>Nº Série</th>
      <th>Marca</th>
      <th>Modelo</th>
      <th>Data de Entrega</th>
      <th>Ações</th>
    </tr>
  </thead>
  <tbody>
    {perifericos.map((periferico) => {
      // Extrai e formata a data manualmente
      const date = new Date(periferico.dataDeEntrega);
      const day = String(date.getDate()).padStart(2, '0'); // Garante 2 dígitos (ex: 05)
      const month = String(date.getMonth() + 1).padStart(2, '0'); // Mês começa em 0
      const year = date.getFullYear();
      const formattedDate = `${day}/${month}/${year}`; // Formato dd/mm/yyyy

      return (
        <tr key={periferico.numeroDeSerie}>
          <td>{periferico.numeroDeSerie}</td>
          <td>{periferico.marca}</td>
          <td>{periferico.modelo}</td>
          <td>{formattedDate}</td> {/* Data formatada aqui */}
          <td>
            <Button 
              variant="danger" 
              size="sm"
              onClick={() => handleDelete(periferico.numeroDeSerie)}
            >
              Excluir
            </Button>
          </td>
        </tr>
      );
    })}
  </tbody>
</Table>
    
    /*/<Table striped bordered hover>
      <thead>
        <tr>
          <th>Nº Série</th>
          <th>Marca</th>
          <th>Modelo</th>
          <th>Data de Entrega</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        {perifericos.map((periferico) => (
          <tr key={periferico.numeroDeSerie}>
            <td>{periferico.numeroDeSerie}</td>
            <td>{periferico.marca}</td>
            <td>{periferico.modelo}</td>
            <td>{new Date(periferico.dataDeEntrega).toLocaleDateString()}</td>
            <td>
              <Button 
                variant="danger" 
                size="sm"
                onClick={() => handleDelete(periferico.numeroDeSerie)}
              >
                Excluir
              </Button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>*/
  );
}
