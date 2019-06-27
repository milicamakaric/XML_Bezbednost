import { Room } from '../models/Room';
import { Agent } from '../models/Agent';
import { Client } from '../models/Client';
export class Reservation{
    id:number;
    startDate: Date;
    endDate: Date;
    totalPrice: number;
    status: string;
    room: Room = new Room();
    agent: Agent = new Agent();
    client: Client = new Client();


}
