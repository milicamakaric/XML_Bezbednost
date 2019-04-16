import {Privilege} from '../models/Privilege';
export class Role
{
    name: string;
    privileges : Array<Privilege>;
}