import { Role } from '../models/Role';
import { Address } from './Address';


export class User {
    id: number;
    name: string;
    surname: string;
    email: string;
    password: string;
    roles : Array<Role>;
	address: Address = new Address();

}