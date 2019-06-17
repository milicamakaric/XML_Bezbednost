import { Privilege } from '../model/Privilege';

export class Role
{
    name: string;
    privileges : Array<Privilege>;
}