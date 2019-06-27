import { PriceForNight } from './PriceForNight';
import { PriceForNightDTO } from './PriceForNightDTO';

export class RoomDTO{
    id: number;
    capacity: number;
    defaultPrice: number;
    specialPrices: Array<PriceForNightDTO> = [];
}