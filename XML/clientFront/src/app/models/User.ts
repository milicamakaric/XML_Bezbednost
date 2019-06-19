import { Role } from '../models/Role';
import { Address } from './Address';
import { Timestamp } from 'rxjs';


export class User {
    id: number;
    name: string;
    surname: string;
    email: string;
    password: string;
    role: string;
    enabled: boolean;
    lastPasswordResetDate: Date;
    roles : Array<Role>;
    address: Address = new Address();

}