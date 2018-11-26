import { Integrator } from '../integrator/integrator';

export class Channel {
    nameChannel: string;
    descriptionChannel: string;
    idChannel: string;
    integrators: Integrator;

    constructor(values: Object = {}){
        Object.assign(this,values);
    }
}
