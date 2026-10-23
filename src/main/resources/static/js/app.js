async function handleSelectChange(select) {

  if(select.value !== 'add_newExpense') return;

  const newExpense = prompt('新しい項目名を入力してください：');

  if(!newExpense || newExpense.trim() === ''){// false：キャンセルまたは未入力のとき
    select.selectedIndex = 0;
    return;
  }

  const trimmed = newExpense.trim();


  const res = await fetch('/category/add', {
    method: 'post',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: trimmed })
  });

  const saved = await res.json();

  const opt = new Option(saved.name, saved.id, true, true);

  select.add(opt);

  console.log(saved);
}


