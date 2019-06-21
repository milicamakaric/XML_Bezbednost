import { AdditionalService } from './AdditionalService';
import { Address } from './Address';
import { AccommodationType } from './AccommodationType';
import { Cancelation } from './Cancelation';

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