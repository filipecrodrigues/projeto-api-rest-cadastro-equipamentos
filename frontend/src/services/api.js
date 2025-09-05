import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api/perifericos',
});

export const getPerifericos = () => api.get('/');
export const createPeriferico = (periferico) => api.post('/', periferico);
export const deletePeriferico = (numeroDeSerie) => api.delete(`/${numeroDeSerie}`);
export const downloadExcel = () => api.get('/excel', { responseType: 'blob' });
