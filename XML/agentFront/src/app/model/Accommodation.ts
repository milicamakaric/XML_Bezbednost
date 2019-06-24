import { AdditionalService } from './AdditionalService';
import { Address } from './Address';
import { AccommodationType } from './AccommodationType';
import { Cancelation } from './Cancelation';
import { Agent } from './Agent';
import { Room } from './Room';

export class Accommodation{
    id: number;
    name: string;
    type: AccommodationType = new AccommodationType();
    description: string;
    image:string;
    additionalServices: Array<AdditionalService> = [];
    address: Address = new Address();
    cancelation: Cancelation = new Cancelation();

}