import axios from 'axios';
import { GET_ERRORS } from './types';

export const createProject = (project, history) => async (disptach) => {
  try {
    const res = await axios.post('http://localhost:8080/api/project', project);
    console.log(res);
    history.push('./dashboard');
  } catch (err) {
    disptach({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
