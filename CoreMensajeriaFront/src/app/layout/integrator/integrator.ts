export class Integrator {
    apiIntegrator: string;
    enabled: boolean;
    id: number;
    messageCost: number;
    nameIntegrator: string;
    threadCapacity: number;

    constructor(values: Object = {}){
        Object.assign( this,values );
    }
}
