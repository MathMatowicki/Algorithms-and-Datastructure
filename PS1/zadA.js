function newtonAlgorithmIteration(n, m) {
    let out = 1;
    for (let i = 1; i <= m; i++) {
        out *= (n - i + 1) / i;
    }
    return out
}
let array = [],
    factorial = 1;

array.push(factorial);

function factorialOfNumber(number) {
    for (let i = 1; i < number; i++) {
        factorial = array[i - 1] * i;
        array.push(factorial);
    }
    return array
}
console.table(factorialOfNumber(25));
console.log(newtonAlgorithmIteration(15, 12));
// console.log(newtonAlgorithmRecursion(2, 1));