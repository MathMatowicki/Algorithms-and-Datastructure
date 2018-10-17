function readFromTxt() {
    document.getElementById('openFile').addEventListener('change', function () {
        const fr = new FileReader();
        fr.onload = function () {
            document.getElementById('fileContents').textContent = this.result;
        }
        fr.readAsText(this.files[0]);
    });
}

function compareNumbers(a, b) {
    return a - b;
}

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min; //The maximum is exclusive and the minimum is inclusive
}

function algorithmA(array) {


    return
}



let inData = [
    [5],
    [7, 2, 11, 4],
    [7, 1, 9, 2],
    [4, 2, 6, 4],
    [1, 1, 2, 2],
    [2, 1, 5, 6],
    [3]
];


console.table(inData);

for (let i = getRandomInt(9999, 10000); i > 0; i--) {
    inData.push(getRandomInt(1, 10000));
}