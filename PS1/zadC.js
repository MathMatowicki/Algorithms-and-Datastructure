function readFromTxt() {
  document.getElementById("openFile").addEventListener("change", function() {
    const fr = new FileReader();
    fr.onload = function() {
      document.getElementById("fileContents").textContent = this.result;
    };
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
  let outData = [0, 0],
    finalSum = array[0],
    sum = 0,
    counterOfConditions = 0;
  for (let i = 1; i < array.length; i++) {
    sum += array[i];
    for (let j = i + 1; j < array.length; j++) {
      counterOfConditions++;
      if (sum + array[j] > finalSum) {
        finalSum += sum + array[j];
        outData[0] = i;
        outData[1] = j + 1;
      }
    }
    sum = 0;
  }
  console.log(counterOfConditions + " counter of Conditions in algorithm A");
  return outData;
}

function algorithmB(array) {
  let max = 0,
    p = 0,
    k = 0,
    sum = 0,
    counter = 0,
    outData = [];
  for (let i = 0; i < array.length; i++) {
    sum += array[i];
    counter++;
    if (sum > max) {
      max = sum;
      outData[0] = i;
    } else {
      outData[1] = i;
    }
  }
  console.log(counter + " counter of Conditions in algorithm B");
  return outData;
}

// function algorithmC(array){
//   let l = array.length, w = 0, s = 0, x=array[0],counter = 0, outData = [];
//   while(l--)
//   {
//     counter++;
//     if(w > 0){
//       outData[1]=l;
//       w += x;
//     } else{
//       w = x;
//           outData[0] = l
//       }
//       if(w > s)
//           s = w;
//   }
//   console.log(counter + " counter of Conditions in algorithm C");
//   console.log(s);
//   if(outData.length != 2){
//     console.log("max element");
//     outData[0] = array.indexOf(Math.max(...array));
//     outData[1] = outData[0];
//   }
//   return outData;
// }

let inDataRandom = [];
let inData = [10, 31, -41, 59, 26, -53, 58, 97, -93, -23, 84];
// let txtData = [10, 31, -41, 59, 26, -53, 58, 97, -93, -23, 84];
for (let i = getRandomInt(9999, 10000); i > 0; i--) {
  inData.push(getRandomInt(-100000, 100000));
}
// console.table(inData);
// let dataMinus = [-10,-9,-8,-7,-6,-5,-4,-3,-2,-1];
inData.splice(0, 1);
console.log(algorithmA(inData));
// console.log(algorithmA(txtData));
// console.log(algorithmA(dataMinus));
console.log(algorithmB(inData));
// console.log(algorithmB(txtData));
// console.log(algorithmB(dataMinus));
console.log(algorithmC(inData));
// console.log(algorithmC(txtData));
// console.log(algorithmC(dataMinus));
