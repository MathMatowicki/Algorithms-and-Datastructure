function sortNumber(a, b) {
    return a - b;
}


function algorithmA(array) {
    let counter = 0;

    array.sort(sortNumber);

    console.log(array);

    for (let i = 0; i < array.length; i++) {
        counter++;
        if (array[i] == array[i + 1]) {
            array[i] += array[i]
            array.slice(i, i + 1);
        }
        i = 0;
    }
    return counter;
}

let inData = [10, 2, 3, 4, 4, 5, 1, 3, 2, 5, 4];

console.log(algorithmA(inData));