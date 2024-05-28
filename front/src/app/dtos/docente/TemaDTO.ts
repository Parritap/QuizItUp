export class TemaDTO{

    tema: string;

    constructor(tema: string){
        this.tema = tema;
    }

    getTema(){
        return this.tema;
    }

    setTema(tema: string){
        this.tema = tema;
    }

}