import { Role } from '../model/Role';
export class User {
    id: number;
    name: string;
    surname: string;
    email: string;
    password: string;
    roles : Array<Role>;
    certificated : boolean;
}
