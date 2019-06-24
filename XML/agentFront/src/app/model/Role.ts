import { Permission } from './Permission';


export class Role
{
    id: number;
    name: string;
    permissions : Array<Permission> = [];
}