import { User } from './User';
import { Address} from './Address';
import { Role } from './Role'; 
import { Accommodation } from './Accommodation';
export class Agent extends User{
    pib: string;
    passChanged: boolean;
    listAccommodation: Array<Accommodation> = [];
}