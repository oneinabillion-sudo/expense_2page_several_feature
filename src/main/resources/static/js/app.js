function handleSelectChange(select) {
  if(select.value !== 'add_newExpense') return;

  const dialog = document.getElementById('categoryDialog');
  const nameInput = document.getElementById('dialogName');

  if(dialog.return)


  nameInput.value = '';// 初期化
  dialog.querySelectorAll('input[name="kind"]').forEach(r => r.checked = false);
  dialog.returnValue = '';
  dialog.addEventListener('close', async () => {/*中で
    ➀「dialogが閉じた理由を確認。」
    ➁ちゃんと追加されたならサーバーに送って、そうでなかったら元に戻す
     */
    const kind = dialog.querySelector('input[name="kind"]:checked');
    const name = nameInput.value.trim();

    if(dialog.returnValue !== 'ok' || !kind || name === ''){// なんかがはいってなかったら
      select.selectedIndex = 0;
      return;
    }

    const res = await fetch('/category/add',{
      method: 'post',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name: name, bool: kind.value === 'true' })
    });
    const saved  = await res.json();
    const opt = new Option(saved.name, saved.id, true, true);
    select.add(opt);
    console.log(saved);

  }, {once: true});

  dialog.showModal();


}


 


