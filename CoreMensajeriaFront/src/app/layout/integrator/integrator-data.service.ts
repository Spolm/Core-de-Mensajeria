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

  public getIntegratorsPerChannel( index: number ): Observable<Integrator[]>{
    return this.api.getIntegratorsPerChannel( index );
  }

  public enabledIntegrator( integrator: Integrator ): Observable<any>{
    return this.api.enabledIntegrator( integrator );
  }

  public disabledIntegrator( integrator: Integrator ): Observable<any>{
    return this.api.disabledIntegrator( integrator );
  }

}
