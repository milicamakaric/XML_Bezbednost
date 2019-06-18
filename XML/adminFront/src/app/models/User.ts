import { Role } from '../models/Role';
import { Address } from '../models/Address';

export class User {
    id: number;
    name: string;
    surname: string;
    email: string;
    passsword: string;
    roles : Array<Role>;
    role: string;
    enabled: boolean;
    address: Address = new Address();
}