import { Room } from './Room';
import { Agent } from './Agent';
import { Client } from './Client';

export class Reservation{
    id: number;
    startDate: Date;
    endDate: Date;
    totalPrice: number;
    status: string;
    room: Room = new Room();
    agent: Agent = new Agent();
    client: Client = new Client();
}