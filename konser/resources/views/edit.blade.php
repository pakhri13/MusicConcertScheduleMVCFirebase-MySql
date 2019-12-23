<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Create</title>      
    </head>
    <body>
     <div class="container">
       <div class="row">
          <div class="col-5">
              <br>
    <form action="{{route('user.update',['$id'=>$konsers->id])}}" method="POST" enctype="multipart/form-data">
      <input type="hidden" name="_method" value="PUT">
        @csrf
        <div class="form-group">
          <label for="formGroupExampleInput">Nama Konser</label>
          <br>
        <textarea name="nama">{{$konsers->nama}}</textarea>
        </div>
        <div class="form-group">
          <label for="formGroupExampleInput">Tempat</label>
          <br>
        <textarea name="tempat">{{$konsers->tempat}}</textarea>
        </div>
        <div class="form-group">
          <label for="formGroupExampleInput2">Tanggal</label>
          <br>
          <textarea name="tanggal">{{$konsers->tanggal}}</textarea>
        </div>
        <div class="form-group">
                <label for="formGroupExampleInput2">Jam</label>
                <br>
                <textarea name="jam">{{$konsers->jam}}</textarea>
        </div>
        <label for="exampleInputFile">
                Input Image
            </label>
            <br>
            <input type="file" name="foto" class="form-control">

            <input type="submit"  class="btn btn-success my-5" value="create">
            
      </form>
          </div>
       </div>
     </div>
      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
    <html>