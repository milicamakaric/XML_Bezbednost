import { User } from './User';
import { Address} from './Address';
import { Role } from './Role'; 
import { AccommodationDTO } from './AccommodationDTO';
export class Agent extends User{
    pib: string;
    passChanged: boolean;
    listAccommodation: Array<AccommodationDTO> = [];
}