import { Agent } from './Agent';
import { Accommodation } from './Accommodation';
import { PriceForNight } from './PriceForNight';

export class Room{
    id: number;
    capacity:number;
    prices: Array<PriceForNight> = [];
    accomodation: Accommodation = new Accommodation();
    agent: Agent = new Agent();
    defaultPrice: number;
}