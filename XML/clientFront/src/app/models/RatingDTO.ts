export class RatingDTO{
    id: number;
    accommodation_id: number;
    client_id: number;
    reservation_id: number;
    rating: number;
    comment: string;
    allowed: boolean;
}