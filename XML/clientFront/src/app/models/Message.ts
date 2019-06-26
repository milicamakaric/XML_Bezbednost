

import { Agent } from './Agent';
import { Client } from './Client';

export class Message{
    id: number;
    content: string;
    agent: Agent = new Agent();
    client: Client = new Client();
    title: string;
    sending: boolean;
}