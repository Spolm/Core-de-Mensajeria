export class Point {
    x: any;
    y: Number;

    constructor(x: any, y: Number) {
        this.x = x;
        this.y = y;
    }

    toJson() {
        return { x: this.x, y: this.y };
    }

    static getXArray(points: Point[]): any[] {
        var x = [];

        points.forEach(point => {
            x.push(point.x);
        });

        return x;
    }

    static getYArray(points: Point[]): Number[] {
        var y = [];

        points.forEach(point => {
            y.push(point.y);
        });

        return y;
    }
}
