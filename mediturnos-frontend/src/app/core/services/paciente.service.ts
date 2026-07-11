import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { ApiResponse } from '../../shared/models/api-response';

import { Paciente } from '../../shared/models/paciente';

@Injectable({
  providedIn:'root'
})
export class PacienteService{

    private http=inject(HttpClient);

    private api='http://localhost:8080/api/v1/pacientes';

    listar():Observable<ApiResponse<Paciente[]>>{

        return this.http.get<ApiResponse<Paciente[]>>(this.api);

    }

}