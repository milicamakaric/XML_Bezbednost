import { Room } from './Room';

export class AccommodationDTO{
    id: number;
    name: string;
    street: string;
    number: string;
    city: string;
    state: string;
    type: string;
    description: string;
    rooms: Array<Room> = [];
    distance: number;
    stars:number;
}