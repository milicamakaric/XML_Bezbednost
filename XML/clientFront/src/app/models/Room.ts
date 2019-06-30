import { AccommodationDTO } from './AccommodationDTO';
import { PriceForNight } from './PriceForNight';
import { Agent } from './Agent';


export class Room{
    id: number;
    capacity:number;
    prices: Array<PriceForNight> = [];
    accommodation: AccommodationDTO = new AccommodationDTO();
    agent: Agent = new Agent();
    defaultPrice: number;
}