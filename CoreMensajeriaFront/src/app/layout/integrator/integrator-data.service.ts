import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Integrator } from './integrator';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class IntegratorDataService {

  integrators: Integrator[] = [];

  constructor(
    private api: ApiService
  ) { }

  public getAllIntegrators(): Observable<Integrator[]>{
    return this.api.getAllIntegrators();
  }
}
