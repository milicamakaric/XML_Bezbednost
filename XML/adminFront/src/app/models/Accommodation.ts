import { AdditionalService } from './AdditionalService';
import { Address } from './Address';

export class Accommodation{
    id: number;
    name: string;
    type: string;
    description: string;
    image:string;
    service: AdditionalService;
    address: Address;
    days:number;

}