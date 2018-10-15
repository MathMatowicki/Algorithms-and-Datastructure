function newtonAlgorithmIteration(n, m) {
    let newton = 0;
    for(let i = 0; i < m; i++){
        newton =newton*(n-i+1)/i;
    }
    return newton;
}

function newtonAlgorithmRecursion(n, m) {}

console.log(newtonAlgorithmIteration(2,1));
console.log(newtonAlgorithmIteration(2,1));
console.log(newtonAlgorithmIteration(2,1));
console.log(newtonAlgorithmIteration(2,1));
console.log(newtonAlgorithmIteration(2,1));
console.log(newtonAlgorithmIteration(2,1));

