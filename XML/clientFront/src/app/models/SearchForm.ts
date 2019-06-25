export class SearchForm {
    city: string;
    startDate: Date;
    endDate: Date;
    numberOfPeople: number;
    type: string;
    distance: number;
    listOfServices: Array<string> = new Array<string>();
    cancelation: string;
    stars: number;
}
