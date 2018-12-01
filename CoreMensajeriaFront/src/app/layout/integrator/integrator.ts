export class Integrator {
    apiIntegrator: string;
    enabled: boolean;
    idIntegrator: number;
    messageCost: number;
    nameIntegrator: string;
    threadCapacity: number;

    constructor(values: Object = {}){
        Object.assign(this,values);
    }
}
