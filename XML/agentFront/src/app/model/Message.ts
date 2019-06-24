
import { Client } from './Client';
import { Agent } from './Agent';

export class Message{
    id: number;
    content: string;
    agent: Agent = new Agent();
    client: Client = new Client();
    title: string;
    sending: boolean;
}