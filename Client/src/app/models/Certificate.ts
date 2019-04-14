export class Certificate {
 id : number;
 idIssuer : number;
 idSubject: number;
 ca : boolean;
 startDate: Date;
 endDate: Date;
 revoked: boolean;
 reasonForRevokation: string;
}
