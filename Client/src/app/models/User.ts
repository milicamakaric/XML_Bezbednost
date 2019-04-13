import { Authority } from './Authority';

export class User {
    id: number;
    name: string;
    surname: string;
    email: string;
    password: string;
    authorities : Array<Authority>;
}
