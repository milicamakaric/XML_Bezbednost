export class ReservationDTO{
    id: number;
    startDate: Date = new Date();
    endDate: Date = new Date();
    totalPrice: number;
    status: string;
}