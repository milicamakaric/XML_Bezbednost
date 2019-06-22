import { User } from './User';
import { Address} from './Address';
import { Role } from './Role'; 
export class Agent{
    pib: string;
    id: number;
    name: string;
    surname: string;
    email: string;
    password: string;
    roles : Array<Role>;
    role: string;
    enabled: boolean;
    deleted: boolean = false;
    blocked: boolean = true;
    address: Address = new Address();
}