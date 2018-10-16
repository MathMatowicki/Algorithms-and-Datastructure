function newtonAlgorithmIteration(n, m) {
    let out =1;
    for(let i = 1; i <= m; i++){
        out *=(n-i+1)/i;
    }
    return out
}

console.log(newtonAlgorithmIteration(2,1));
console.log(newtonAlgorithmRecursion(2,1));