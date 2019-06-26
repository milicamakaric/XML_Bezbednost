import { Room } from './Room';
import { RoomDTO } from './RoomDTO';

export class AccommodationDTO {
    id: number;
    name: string;
    street: string;
    number: string;
    city: string;
    state: string;
    type: string;
    description: string;
    rooms: Array<RoomDTO> = [];
    distance: number;
    stars: number;
}
