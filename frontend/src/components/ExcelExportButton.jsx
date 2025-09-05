import { BsDownload } from 'react-icons/bs';
import { Button } from 'react-bootstrap';
import { downloadExcel } from '../services/api';

export default function ExcelExportButton() {
  const handleDownload = async () => {
    try {
      const response = await downloadExcel();
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'perifericos.xlsx');
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (error) {
      console.error('Erro ao exportar:', error);
    }
  };

  return (
    <Button variant="success" onClick={handleDownload}>
      <BsDownload className="me-2" /> Exportar Excel
    </Button>
  );
}
