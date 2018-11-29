export class Integrator {
    apiIntegrator: string;
    enabled: boolean;
    idIntegrator: string;
    messageCost: string;
    nameIntegrator: string;
    threadCapacity: string;

    constructor(values: Object = {}){
        Object.assign(this,values);
    }
}
