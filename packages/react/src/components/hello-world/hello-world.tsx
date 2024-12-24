export interface Props {
  msg?: string
  name: 'pkmer' | 'hzz'
}

export const HelloWorld: React.FC<Props> = props => {
  function handleClick() {
    console.log('更新了吗???')
  }
  return (
    <div className='hello-world'>
      <span className='text-green-600'>hello world</span> {props.msg} {props.name}
      <button onClick={handleClick}>click Me</button>
    </div>
  )
}
