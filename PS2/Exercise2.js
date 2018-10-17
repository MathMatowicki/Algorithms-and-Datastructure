let array = [], factorial = 1;

array.push(factorial);


function factorialOfNumber(number){
    for (let i = 1; i < number; i++) {
        factorial = array[i-1] *i;
        array.push(factorial); 
    }
    return array
}

console.table(factorialOfNumber(25));