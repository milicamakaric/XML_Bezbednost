import { Role } from '../models/Role';
<<<<<<< HEAD
import { Address } from './Address';

=======
import { Address } from '../models/Address';
>>>>>>> 9ee684a96cfa6e167bf79716894a23274239ff1c

export class User {
    id: number;
    name: string;
    surname: string;
    email: string;
    passsword: string;
    roles : Array<Role>;
<<<<<<< HEAD
	address: Address = new Address();

=======
    role: string;
    enabled: boolean;
    deleted: boolean = false;
    blocked: boolean = true;
    address: Address = new Address();
>>>>>>> 9ee684a96cfa6e167bf79716894a23274239ff1c
}