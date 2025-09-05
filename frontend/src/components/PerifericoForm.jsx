import { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';
import { createPeriferico } from '../services/api';

export default function PerifericoForm({ onAdd }) {
  const [show, setShow] = useState(false);
  const [periferico, setPeriferico] = useState({
    numeroDeSerie: '',
    marca: '',
    modelo: '',
    dataDeEntrega: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createPeriferico(periferico);
      onAdd();
      handleClose();
    } catch (error) {
      console.error('Erro ao cadastrar:', error);
    }
  };

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <>
      <Button variant="primary" onClick={handleShow} className="mb-3">
        Cadastrar
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>EQUIPAMENTO</Modal.Title>
        </Modal.Header>
        <Form onSubmit={handleSubmit}>
          <Modal.Body>

            <Form.Group className="mb-3">
              <Form.Label>Número de Série</Form.Label>
              <Form.Control
                type="text"
                value={periferico.numeroDeSerie}
                onChange={(e) => setPeriferico({...periferico, numeroDeSerie: e.target.value})}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Marca</Form.Label>
              <Form.Control
                type="text"
                value={periferico.marca}
                onChange={(e) => setPeriferico({...periferico, marca: e.target.value})}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Modelo</Form.Label>
              <Form.Control
                type="text"
                value={periferico.modelo}
                onChange={(e) => setPeriferico({...periferico, modelo: e.target.value})}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Data de Entrega</Form.Label>
              <Form.Control
                type="date"
                value={periferico.dataDeEntrega}
                onChange={(e) => setPeriferico({...periferico, dataDeEntrega: e.target.value})}
                required
              />
            </Form.Group>

          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Fechar
            </Button>
            <Button variant="primary" type="submit">
              Salvar
            </Button>
          </Modal.Footer>
        </Form>
      </Modal>
    </>
  );
}
